package za.ac.cput.service;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import za.ac.cput.domain.Wishlist;
import za.ac.cput.repository.WishlistRepository;

import java.util.List;
import java.util.stream.Collectors;

/**
 * WishlistService.java
 * <p>
 * This service handles operations for managing Wishlist entities.
 * It includes methods for creating, reading, updating, and deleting Wishlists.
 *
 * @author Rethabile Ntsekhe
 * Student Num: 220455430
 * @date 25-Aug-24
 */
@Service
@Transactional
public class WishlistService implements iWishlist {

    private final WishlistRepository wishlistRepository;

    @Autowired
    public WishlistService(WishlistRepository wishlistRepository) {
        this.wishlistRepository = wishlistRepository;
    }

    /**
     * Create a new wishlist.
     *
     * @param wishlist the wishlist to create
     * @return the saved wishlist
     */
    @Override
    public Wishlist create(Wishlist wishlist) {
        return wishlistRepository.save(wishlist);
    }

    /**
     * Read and retrieve a wishlist by its ID.
     * Lazily loads WishlistItems and their related Product to avoid LazyInitializationException.
     *
     * @param id the ID of the wishlist to retrieve
     * @return the found wishlist
     * @throws EntityNotFoundException if the wishlist is not found
     */
    @Override
    @Transactional(readOnly = true)
    public Wishlist read(Long id) {
        return wishlistRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Wishlist not found"));
    }

    /**
     * Update an existing wishlist.
     *
     * @param wishlist the wishlist with updated data
     * @return the updated wishlist
     * @throws IllegalArgumentException if the wishlist with the provided ID does not exist
     */
    @Override
    public Wishlist update(Wishlist wishlist) {
        // Check if the wishlist exists in the repository
        Wishlist existingWishlist = wishlistRepository.findById(wishlist.getId()).orElse(null);
        if (existingWishlist != null) {
            Wishlist updatedWishlist = new Wishlist.Builder()
                    .copy(existingWishlist)
                    .setUser(wishlist.getUser())
                    .setProduct(wishlist.getProduct())
                    .build();
            return wishlistRepository.save(updatedWishlist);
        } else {
            throw new IllegalArgumentException("Wishlist with ID " + wishlist.getId() + " does not exist");
        }
    }

    /**
     * Delete a wishlist and wishlist Items by its ID.
     *
     * @param id the ID of the wishlist to delete
     * @return
     */
    public boolean delete(Long id) {

        wishlistRepository.deleteById(id);

        // Check if the entity still exists after deletion
        boolean exists = wishlistRepository.existsById(id);

        // Return false if entity was deleted successfully, otherwise return true
        return !exists;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Wishlist> findByUserId(Long userId) {
        return wishlistRepository.findByUserId(userId);
    }

    /**
     * Retrieve all wishlists.
     *
     * @return a list of all wishlists
     */
    @Override
    @Transactional(readOnly = true)
    public List<Wishlist> findAll() {
        return wishlistRepository.findAll();
    }
}
