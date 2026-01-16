package in.karthik.Iservice.imple;

import java.util.List;

import org.apache.catalina.mapper.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.karthik.Iservice.IproductService;
import in.karthik.dto.ProductDto;
import in.karthik.entity.Product;
import in.karthik.exception.ProductNotFoundException;
import in.karthik.repo.ProductRepo;
import jakarta.transaction.Transactional;
@Service
@Transactional
public class IserviceImple implements IproductService{
	
	@Autowired
	private ModelMapper mapper;
	
	@Autowired
	private ProductRepo productRepo;
	
	
	/*here we get data in DTO but we can't save DTO directly in DB(WKT,DB mapped with entity)
	so, we need to convert to entity
	use map() present in ModelMapper with takes 2 parameter
	1)object you need to convert(DTO)
	2)the class you want to convert into (Entity) (give ->.class)
	*/
	@Override
	public ProductDto save(ProductDto productDto) {		
		Product product=mapper.map(productDto, Product.class);//returnType entity(Dto->entity)
		productRepo.save(product);
		return mapper.map(product,ProductDto.class);//here returnType Dto(converted from entity->Dto)
	}

	
	
	@Override
	public List<ProductDto> getAll() {
		List<Product> productsEntity=productRepo.findAll();
		
		/*-->list of Product entities into a stream
		 *-->For each product entity in the stream convert to ProductDto using map()
		 *-->Converts the stream back into a List*/
		
		List<ProductDto> productDtos=productsEntity.stream() 
				.map(product->mapper.map(product,ProductDto.class))
				.toList();
		return productDtos;
	}

	
	
	@Override
	public ProductDto getById(Integer productid) throws Exception {	
		Product productinfo = productRepo.findById(productid)
		        .orElseThrow(() -> new ProductNotFoundException("Product not found"));
		return mapper.map(productinfo, ProductDto.class);//entity->dto
	}

	
	
	@Override
	public void update(ProductDto productDto, Integer productid) throws Exception {
		Product productinfo = productRepo.findById(productid)
		        .orElseThrow(() -> new ProductNotFoundException("Product not found"));	
		
		productRepo.save(mapper.map(productDto, Product.class));
		
	}

	@Override
	public void delete(Integer productid) throws Exception {
		if(!productRepo.findById(productid).isPresent()) {
			throw new ProductNotFoundException("product not found");
		}
		productRepo.deleteById(productid);
	}

}
