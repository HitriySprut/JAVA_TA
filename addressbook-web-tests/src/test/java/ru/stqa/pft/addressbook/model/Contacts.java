package ru.stqa.pft.addressbook.model;

import com.google.common.collect.ForwardingSet;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by admin on 28.06.2018.
 */
public class Contacts extends ForwardingSet<ContactData> {

  private Set<ContactData> delegate;
  @Override
  protected Set<ContactData> delegate() {
    return delegate;
  }

  public Contacts(Contacts contacts){
    this.delegate=new HashSet<>(contacts.delegate);
  }

  public Contacts(List contacts){
    this.delegate=new HashSet<>(contacts);
  }

  public Contacts(){
    this.delegate=new HashSet<>();
  }


  public Contacts withAdded( ContactData contact){
    Contacts contacts = new Contacts(this);
    contacts.add(contact);
    return contacts;
  }

  public Contacts without(ContactData contact) {
    Contacts contacts = new Contacts(this);
    contacts.remove(contact);
    return contacts;
  }

}
