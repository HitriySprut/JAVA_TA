package ru.stqa.pft.addressbook.model;

import com.google.common.collect.ForwardingSet;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by admin on 24.06.2018.
 */
public class Groups extends ForwardingSet<GroupData> {

  private Set<GroupData> delegate;
  @Override
  protected Set<GroupData> delegate() {
    return delegate;
  }

  public Groups(Groups groups){
    this.delegate=new HashSet<>(groups.delegate);
  }

  public Groups (Collection groups){
    this.delegate=new HashSet<>(groups);
  }
  public Groups(){
    this.delegate=new HashSet<>();
  }


  public Groups withAdded( GroupData group){
    Groups groups = new Groups(this);
    groups.add(group);
    return groups;
  }

  public Groups without(GroupData deletedGroup) {
    Groups groups = new Groups(this);
    groups.remove(deletedGroup);
    return groups;
  }
}
