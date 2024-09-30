package za.ac.cput.factory;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import za.ac.cput.domain.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class WishlistFactoryTest {

    private User user;
    private Product product;
    private Wishlist wishlist;
    private List<WishlistItem> wishlistItems;
    private Category category;
    private List<SubCategory> subCategory;

    @BeforeEach
    void setup() {
        wishlist = new Wishlist();
        // Create a sample Category object using the factory method
        category = new Category();

        // Create sample SubCategory objects using the factory method
        SubCategory subCategory1 = new SubCategory();

        SubCategory subCategory2 = new SubCategory();


        // Create a sample Product object using the factory method
        product = new Product();

        // Set up a sample User object
        user = new User.Builder()
                .setFirstName("John")
                .setLastName("Doe")
                .setEmail("johndoe@example.com")
                .setPassword("password123")
                .build();

        // Create WishlistItem objects using the factory method
        WishlistItem wishlistitem1 = WishlistItemFactory.createWishlistItem(
                product,
                wishlist,
                LocalDateTime.now()
        );

        WishlistItem wishlistitem2 = WishlistItemFactory.createWishlistItem(
                product,
                wishlist,
                LocalDateTime.now()
        );

        wishlistItems = new ArrayList<>();
        wishlistItems.add(wishlistitem1);
        wishlistItems.add(wishlistitem2);
    }

    @Test
    void testCreateWishlist() {
        // Create a Wishlist object using the factory method
        wishlist = WishlistFactory.createWishlist(
                1L,
                user,
                wishlistItems,
                LocalDateTime.now(),
                null);

        // Verify that the Wishlist object is not null
        assertNotNull(wishlist);

        // Print the created Wishlist object to the terminal
        System.out.println("Created Wishlist: " + wishlist);
    }

    @Test
    void testCreateWishlist_WithNullUser_ThrowsIllegalArgumentException() {
        // Try to create a Wishlist object with null user
        assertThrows(IllegalArgumentException.class,
                () -> WishlistFactory.createWishlist(
                        1L,
                        null,
                        wishlistItems,
                        LocalDateTime.now(),
                        null)
        );

        // Print a message to the terminal indicating that an exception was thrown
        System.out.println("Expected IllegalArgumentException thrown when creating Wishlist with null user");
    }

    @Test
    void testCreateWishlist_WithNullItems_ThrowsIllegalArgumentException() {
        // Try to create a Wishlist object with null wishlist items
        assertThrows(IllegalArgumentException.class,
                () -> WishlistFactory.createWishlist(
                        1L,
                        user,
                        null,
                        LocalDateTime.now(),
                        null)
        );

        System.out.println("Expected IllegalArgumentException thrown when creating Wishlist with null items");
    }
}
