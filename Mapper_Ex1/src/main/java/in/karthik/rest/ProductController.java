package in.karthik.rest;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.karthik.Iservice.IproductService;
import in.karthik.dto.ProductDto;
import in.karthik.exception.ProductNotFoundException;

@RestController
@RequestMapping("/api")
public class ProductController {
	
	@Autowired
	private IproductService productService;
	
	@PostMapping("/addProduct")
	public ResponseEntity<ProductDto> addProduct(@RequestBody ProductDto productDto){
		ProductDto res=productService.save(productDto);
		return ResponseEntity.ok().body(res);
	}
	
	@GetMapping("/allProduct")
	public ResponseEntity<List<ProductDto>> listAllproducts(){
		List<ProductDto> productDtoList=productService.getAll();
		return ResponseEntity.ok().body(productDtoList);		
	}
	
	
	
	
	@GetMapping("/product/{productId}")
	public ResponseEntity<?> getProductById(@PathVariable Integer productId) throws Exception{
		try {
			ProductDto productDto=productService.getById(productId);
			return ResponseEntity.ok(productDto);
		}catch(Exception e) {
			String msg=e.getMessage();
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(msg);
		}	
	}
	
	
	
	@PutMapping("/updateProduct/{id}")
	public ResponseEntity<String> updateProduct(@RequestBody ProductDto productDto, 
	@PathVariable("id") Integer productId) throws Exception{
		productService.update(productDto, productId);
		String res = "Product updated successfully";
		return ResponseEntity.ok().body(res);	
	}
	
	
	
	@DeleteMapping("/deleteProduct/{productId}")
	public ResponseEntity<String> deleteProduct(@PathVariable Integer productId){
		try {
			productService.delete(productId);
			return ResponseEntity.ok("Deleted Successfully");
		} catch (Exception e) {
			String msg=e.getMessage();
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(msg);		
		}	
	}
}
