package com.example.vaadin_faq;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import com.google.common.collect.ImmutableList;
import com.vaadin.Application;
import com.vaadin.data.Item;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.Component;
import com.vaadin.ui.DefaultFieldFactory;
import com.vaadin.ui.Field;
import com.vaadin.ui.Form;
import com.vaadin.ui.Label;
import com.vaadin.ui.NativeSelect;
import com.vaadin.ui.OptionGroup;
import com.vaadin.ui.Table;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

public class Vaadin_faqApplication extends Application {

  public static class Contact {
    String name;
    public Contact(final String name) {
      this.name = name;
    }
    public String getName() {
      return name;
    }
    public void setName(final String name) {
      this.name = name;
    }
  }

  public static class Person {
    String name;
    Collection<Contact> contacts = new ArrayList();
    public Person(final String name, final Collection<Contact> contacts) {
      this.name = name;
      this.contacts = contacts;
    }
    public Collection<Contact> getContacts() {
      return contacts;
    }
    public String getName() {
      return name;
    }
    public void setContacts(final Collection<Contact> contacts) {
      this.contacts = contacts;
    }
    public void setName(final String name) {
      this.name = name;
    }
  }

  static Component label(final String string) {
    final Label label = new Label();
    label.setValue(string);
    return label;
  }

  @Override
  public void init() {
    setTheme("qa");
    final Window mainWindow = new Window("Vaadin_faq Application");
    final Label label = new Label("Hello Vaadin user");
    mainWindow.addComponent(label);
    setMainWindow(mainWindow);
    final Form form = new Form(new VerticalLayout(), new DefaultFieldFactory() {
      @Override
      public Field createField(final Item item, final Object propertyId, final Component uiContext) {
        if ("contacts".equals(propertyId)) {
          final Table table = new Table();
          table.setSizeFull();
          // table.setContainerDataSource(new BeanItemContainer<Contact>(Contact.class,
          // (Collection<? extends Contact>) item.getItemProperty(propertyId).getValue()));
          return table;
        } else {
          return super.createField(item, propertyId, uiContext);
        }
      }
    });
    final Person p = new Person("Hans Karl", Arrays.asList(new Contact[]{
        new Contact("Musterstrasse 3"),
        new Contact("Sonstwo 123")
    }));
    form.setItemDataSource(new BeanItem<Person>(p));
    mainWindow.addComponent(form);

    final FakeHeader fh = new FakeHeader(5, new String[]{
        "Bla",
        "Bla2 dfasfasdfas f asfda sfasdf",
        "Bla3",
        "Bla4",
        "Bla5"
    });
    fh.setColumnWidth(0, "220px");
    fh.setColumnWidth(1, "120px");
    fh.setColumnWidth(2, "320px");
    fh.setColumnWidth(3, "50px");
    fh.setColumnWidth(4, "70px");

    fh.setColumnFilter(4, new TextField());
    fh.setColumnFilter(2, new NativeSelect(null, ImmutableList.of("A", "B", "C")));
    fh.setColumnFilter(1, new OptionGroup(null, ImmutableList.of("X", "Y", "Z")));

    mainWindow.addComponent(fh);
  }
}