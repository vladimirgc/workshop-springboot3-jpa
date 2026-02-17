package com.educandoweb.course.services;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.List;
import java.util.Optional;

import org.krysalis.barcode4j.impl.code128.Code128Bean;
import org.krysalis.barcode4j.output.bitmap.BitmapCanvasProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;


import com.educandoweb.course.entities.Product;
import com.educandoweb.course.repositories.ProductRepository;
import com.educandoweb.course.services.exceptions.DatabaseException;
import com.educandoweb.course.services.exceptions.ResourceNotFoundException;



import jakarta.persistence.EntityNotFoundException;

@Service
public class ProductService {
	
	@Autowired
	private ProductRepository repository;
	
	public List<Product> findAll(){
		
		return repository.findAll();
		
	}
	
	public Product findById(Long id) {
		Optional<Product> obj = repository.findById(id);
		return obj.get();
	}
	
	public Product insert(Product obj) {
		return repository.save(obj);
	}
	
	public Product update(Long id, Product obj) {
		try {
		Product entity = repository.getReferenceById(id);
		updateData(entity, obj);
		return repository.save(entity);
		}catch(EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}
	}
	
	private void updateData(Product entity, Product obj) {
		entity.setName(obj.getName());
		entity.setDescription(obj.getDescription());
		entity.setPrice(obj.getPrice());
		entity.setImgUrl(obj.getImgUrl());
		entity.setBarCode(obj.getBarCode());
	}
	
	public void delete(Long id) {
		Optional<Product> obj = repository.findById(id);
		if (obj.isPresent()) {
			Product product = obj.get();
			if (product.getImgUrl() != null && !product.getImgUrl().isEmpty()) {
	            String uploadDir = "uploads/products/";
	            String imageUrl = product.getImgUrl();
	            String fileName = imageUrl.substring(imageUrl.lastIndexOf("/") + 1);
	            File file = new File(uploadDir + fileName);

	            if (file.exists()) {
	                file.delete();
	            }
	        }
			try {
				repository.deleteById(id);
			} catch (DataIntegrityViolationException e) {
				throw new DatabaseException(e.getMessage());
			}
		} else {
			throw new ResourceNotFoundException(id);
		}
	} 

	public List<Product> findByCategoryId(Long categoryId) {		
		
	    if (categoryId != null) {
	        return repository.findByCategoryId(categoryId);
	    }

	    return repository.findAll();
	}

	 public BufferedImage generateBarcodeImage(String code) {
	        try {
	            Code128Bean barcodeGenerator = new Code128Bean();

	            // Configurações visuais
	            barcodeGenerator.setModuleWidth(0.2); // largura de cada traço
	            barcodeGenerator.setBarHeight(60);    // altura do código de barras
	            barcodeGenerator.doQuietZone(true);   // espaço em branco

	            // Canvas para gerar imagem em memória
	            BitmapCanvasProvider canvas = new BitmapCanvasProvider(
	                    300,                      // dpi
	                    BufferedImage.TYPE_BYTE_BINARY,
	                    false,
	                    0
	            );

	            // Gera a imagem
	            barcodeGenerator.generateBarcode(canvas, code);
	            canvas.finish();

	            return canvas.getBufferedImage();

	        } catch (Exception e) {
	            e.printStackTrace();
	            return null;
	        }
	    }
	
	 public String generateEAN13() {
		    StringBuilder sb = new StringBuilder();
		    for (int i = 0; i < 12; i++) {
		        sb.append((int)(Math.random() * 10));
		    }
		    int checkDigit = calculateCheckDigit(sb.toString());
		    return sb.toString() + checkDigit;
		}

		private int calculateCheckDigit(String code) {
		    int sum = 0;
		    for (int i = 0; i < code.length(); i++) {
		        int digit = Character.getNumericValue(code.charAt(i));
		        sum += (i % 2 == 0) ? digit : digit * 3;
		    }
		    int mod = sum % 10;
		    return (mod == 0) ? 0 : 10 - mod;
		}

		public Product findByBarcode(String barCode) {
		    return repository.findByBarCode(barCode)
		                     .orElse(null);
		}
	
}
