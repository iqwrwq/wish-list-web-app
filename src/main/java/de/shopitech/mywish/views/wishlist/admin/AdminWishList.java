package de.shopitech.mywish.views.wishlist.admin;

import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import de.shopitech.mywish.views.MainLayout;
import jakarta.annotation.security.PermitAll;

@PageTitle("Shopitech-My-Wish Admin")
@Route(value = "admin-wishlist", layout =  MainLayout.class)
@PermitAll
public class AdminWishList {
}
