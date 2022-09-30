package site.metacoding.firstapp.domain.product;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.context.annotation.Import;

import site.metacoding.firstapp.config.MyBatisConfig;

@Import(MyBatisConfig.class) // MyBatisTest가 MyBatisConfig를 못읽음
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE) // 실DB사용
@MybatisTest
public class ProductDaoTest {

  @Autowired
  private ProductDao productDao;

  // 호출할 메서드에 _test 를 붙이는게 규칙
  @Test
  public void findById_test() {
    // given, 호출 메서드가 받아야할 것
    Integer productId = 1;

    // when, 테스트를 하고
    Product productPS = productDao.findById(productId);

    // then, 검증을 한다
    assertEquals("바나나", productPS.getProductName());

  }

  @Test
  public void findAll_test() {
    // given

    // when
    List<Product> productPS = productDao.findAll();

    // then
    assertEquals(2, productPS.size());

  }

  @Test
  public void insert_test() {
    String name = "수박";
    Integer price = 1800;
    Integer qty = 88;

    // given
    Product product = new Product(name, price, qty);
    // when
    int result = productDao.insert(product);
    // then
    assertEquals(1, result);
  }

  @Test
  public void update_test() {
    // given
    Integer productId = 1;
    String name = "수박";
    Integer price = 1800;
    Integer qty = 88;

    Product product = new Product(name, price, qty); // 업데이트 할 내용
    product.setProductId(productId);

    // verify : Update 경우에, 영속화( DB에 있는지 체크 ) 시켜야함
    Product productPS = productDao.findById(productId);
    assertTrue(productPS == null ? false : true);

    // when : Update 실제 테스트
    product.update(product);
    int result = productDao.update(productPS);

    // then
    assertEquals(1, result);
  }

  @Test
  public void delete_test() {
    // given
    Integer productId = 1;

    Product productPS = productDao.findById(productId);
    assertTrue(productPS == null ? false : true);

    // when
    int result = productDao.deleteById(productId);

    // then
    assertEquals(1, result);
  }

}
