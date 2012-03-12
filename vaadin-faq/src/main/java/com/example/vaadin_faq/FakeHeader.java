package com.example.vaadin_faq;

import com.google.common.base.Objects;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Component;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

public class FakeHeader extends CustomComponent {

  private final VerticalLayout header;
  private final int columns;
  private final String[] columnCaption;
  private final String[] columnWidth;
  private final Component[] columnFilter;

  public FakeHeader(final int columns, final String[] captions) {
    this(columns, captions, new Component[columns], new String[columns]);
  }

  public FakeHeader(final int columns, final String[] captions, final Component[] filters, final String[] widths) {
    header = new VerticalLayout();
    header.setSpacing(false);
    setCompositionRoot(header);
    this.columns = columns;
    this.columnCaption = captions;
    this.columnWidth = widths;
    this.columnFilter = filters;
    buildHeader();
  }

  public void setColumnCaption(final int i, final String string) {
    columnCaption[i] = string;
    buildHeader();
  }

  public void setColumnFilter(final int i, final Component filter) {
    columnFilter[i] = filter;
    buildHeader();
  }

  public void setColumnWidth(final int i, final String string) {
    columnWidth[i] = string;
    buildHeader();
  }

  private void buildHeader() {
    header.removeAllComponents();
    final GridLayout headerCaption = new GridLayout(columns, 1);
    // headerCaption.setWidth("700px");
    headerCaption.setRowExpandRatio(0, 1.0f);
    headerCaption.addStyleName("fakeheader");
    headerCaption.addStyleName("caption");
    for (int i = 0; i < columns; i++) {
      final Label l1 = new Label(columnCaption[i]);
      l1.setWidth(Objects.firstNonNull(columnWidth[i], "-1"));
      headerCaption.addComponent(l1, i, 0);
      headerCaption.setComponentAlignment(l1, Alignment.MIDDLE_CENTER);
    }
    header.addComponent(headerCaption);

    final GridLayout headerFilter = new GridLayout(columns, 1);
    // headerFilter.setWidth("700px");
    headerFilter.setRowExpandRatio(0, 1.0f);
    headerFilter.addStyleName("fakeheader");
    headerFilter.addStyleName("filter");
    for (int i = 0; i < columns; i++) {
      final Component filterComponent = columnFilter[i];
      if (filterComponent != null) {
        final VerticalLayout vl = new VerticalLayout();
        vl.setWidth(Objects.firstNonNull(columnWidth[i], "-1"));
        filterComponent.setWidth("80%");
        vl.addComponent(filterComponent);
        vl.setComponentAlignment(filterComponent, Alignment.MIDDLE_CENTER);
        headerFilter.addComponent(vl, i, 0);
        headerFilter.setComponentAlignment(vl, Alignment.MIDDLE_CENTER);
      } else {
        final Label l1 = new Label();
        l1.setWidth(Objects.firstNonNull(columnWidth[i], "-1"));
        headerFilter.addComponent(l1, i, 0);
        headerFilter.setComponentAlignment(l1, Alignment.MIDDLE_CENTER);
      }
    }
    header.addComponent(headerFilter);
  }
}