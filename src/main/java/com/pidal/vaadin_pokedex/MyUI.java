package com.pidal.vaadin_pokedex;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Button;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Grid.SelectionMode;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

/**
 * This UI is the application entry point. A UI may either represent a browser window 
 * (or tab) or some part of an HTML page where a Vaadin application is embedded.
 * <p>
 * The UI is initialized using {@link #init(VaadinRequest)}. This method is intended to be 
 * overridden to add component to the user interface and initialize non-component functionality.
 */
@Theme("mytheme")
public class MyUI extends UI {

	private Pokemon selectedPokemon; 
	
    @Override
    protected void init(VaadinRequest vaadinRequest) {
       
    	Grid<Pokemon> grid = new Grid<Pokemon>();
    	
    	HorizontalLayout horizontalLayout = new HorizontalLayout();
    	
    	/* VENTANA DETALLE */
    	
    	Window subWindow = new Window("Pokemon details");
        VerticalLayout subContent = new VerticalLayout();
        
        Label labelNumber = new Label();
        Label labelName = new Label();
        Label labelType = new Label();
        Button buttonDelete = new Button("Delete pokemon");
        
        buttonDelete.addClickListener(e -> {
        	Pokedex.getInstance().deletePokemon(selectedPokemon);
        	grid.setItems(Pokedex.getInstance().getPokemons());
        	removeWindow(subWindow);
        });
        
      
        subContent.addComponents(labelNumber, labelName, labelType, buttonDelete);
        
        
        subWindow.center();
        subWindow.setContent(subContent);
        
        
        
        // addWindow(subWindow);
    	
    	/* TABLE */
    	
    	
    	grid.addColumn(Pokemon::getNumber).setCaption("Number");
    	grid.addColumn(Pokemon::getName).setCaption("Name");
    	grid.addColumn(Pokemon::getType).setCaption("Type");
    	grid.setSelectionMode(SelectionMode.SINGLE);
    	
    	grid.addItemClickListener(event -> {
    		
    		selectedPokemon = event.getItem();
    		
        	// Notification.show("Value: " + event.getItem());
        	labelNumber.setValue(selectedPokemon.getNumber());
        	labelName.setValue(selectedPokemon.getName());
        	labelType.setValue(selectedPokemon.getType());
        	
        	
        	removeWindow(subWindow);
        	addWindow(subWindow);
        	
    	});
    	
    	
    	/* FORM */
    	
    	
    	FormLayout formLayout = new FormLayout();
    	
    	TextField textFieldNumber = new TextField("Number");
    	TextField textFieldName = new TextField("Name");
    	TextField textFieldType = new TextField("Type");
    	Button buttonAddPokemon = new Button("Añadir");
    			
    	buttonAddPokemon.addClickListener(e -> {
    		
    		Pokemon p = new Pokemon(
    				textFieldNumber.getValue(),
    				textFieldName.getValue(),
    				textFieldType.getValue()
    				);
    		
    		Pokedex.getInstance().addPokemon(p);
    		
    		textFieldNumber.clear();
    		textFieldName.clear();
    		textFieldType.clear();
    		
    		grid.setItems(Pokedex.getInstance().getPokemons());
    		
    		
    		Notification.show("Pokemon capturado! Ya tenemos " + 
    				Pokedex.getInstance().getPokemons().size() + "!!",
    				Notification.TYPE_TRAY_NOTIFICATION);
    		
    	});
    	
    	
    	
    	formLayout.addComponents(
    			textFieldNumber, 
    			textFieldName, 
    			textFieldType, 
    			buttonAddPokemon
    	);
    	
    
    	horizontalLayout.addComponents(grid, formLayout);
    	
    	
    	
    	setContent(horizontalLayout);
    	
    	
    }

    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }
}
