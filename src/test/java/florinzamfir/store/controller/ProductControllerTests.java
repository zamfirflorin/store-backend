package florinzamfir.store.controller;

import florinzamfir.store.model.Product;
import florinzamfir.store.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProductController.class)
@ExtendWith(SpringExtension.class)
public class ProductControllerTests {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService productService;

    private Product product;
    private List<Product> productList;

    @BeforeEach
    void setUp() {
        product = new Product(1L, "Test Product", 10.00, "Description" );
        productList = Arrays.asList(product);
    }

//    @Test
//    @WithMockUser(username = "admin", roles = {"ADMIN"})
//    void testAddProduct() throws Exception {
//        when(productService.addProduct(any())).thenReturn(product);
//        mockMvc.perform(post("/products")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content("{\"name\":\"Test Product\",\"description\":\"Description\",\"price\":10.00}"))
//                .andExpect(status().isCreated())
//                .andExpect(jsonPath("$.name", is(product.getName())));
//    }
//
//    @Test
//    @WithMockUser(username = "admin", roles = {"ADMIN"})
//    void testGetProductById() throws Exception {
//        when(productService.addProduct(any())).thenReturn(product);
//        mockMvc.perform(get("/products/1"))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.name", is(product.getName())));
//    }
//
//    @Test
//    @WithMockUser(username = "customer", roles = {"ADMIN"})
//    void testGetAllProducts() throws Exception {
//        when(productService.getAllProducts()).thenReturn(productList);
//        mockMvc.perform(get("/products"))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$[0].name", is(product.getName())));
//    }
//
//    @Test
//    @WithMockUser(username = "admin", roles = {"ADMIN"})
//    void testDeleteProductById() throws Exception {
//        doNothing().when(productService).deleteProductById(anyLong());
//        mockMvc.perform(delete("/products/1"))
//                .andExpect(status().isNoContent());
//    }
//
//    @Test
//    @WithMockUser(username = "admin", authorities = {"ROLE_ADMIN"})
//    void testUpdateProduct() throws Exception {
//        when(productService.updateProduct(any())).thenReturn(product);
//        mockMvc.perform(put("/products/1")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content("{\"id\":1,\"name\":\"Updated Product\",\"description\":\"Description\",\"price\":10.00}"))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.name", is("Updated Product")));
//    }

    @Test
    @WithMockUser(username = "customer", roles = {"CUSTOMER"})
    void testUnauthorizedAccess() throws Exception {
        mockMvc.perform(post("/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"Test Product\",\"description\":\"Description\",\"price\":10.00}"))
                .andExpect(status().isForbidden());
    }
}
