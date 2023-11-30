package de.shopitech.mywish.views.wishlist;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.HasUrlParameter;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import de.shopitech.mywish.data.entity.Benutzer;
import de.shopitech.mywish.data.repository.BenutzerRepository;
import de.shopitech.mywish.views.MainLayout;
import jakarta.annotation.security.PermitAll;

import java.util.Optional;

@PageTitle("Shopitech-My-Wish")
@Route(value = "user-wishlist", layout = MainLayout.class)
@PermitAll
public class BenutzerWishList extends Div implements HasUrlParameter<String> {

    private BenutzerRepository benutzerRepository;
    private Optional<Benutzer> benutzer;

    public BenutzerWishList(BenutzerRepository benutzerRepository) {
        this.benutzerRepository = benutzerRepository;
    }

    @Override
    public void setParameter(BeforeEvent beforeEvent, String parameter) {
        benutzer = parameter != null ? benutzerRepository.findById(Long.decode(parameter)) : Optional.empty();

        if (benutzer.isPresent()) {
        } else {
            getUI().ifPresent(ui -> ui.navigate("not-found"));
        }
    }
}
