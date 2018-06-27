package ru.stqa.pft.addressbook.model;

import com.google.common.collect.ForwardingSet;

import java.util.Set;

/**
 * Created by admin on 24.06.2018.
 */
public class Groups extends ForwardingSet<GroupData> {
  @Override
  protected Set<GroupData> delegate() {
    return null;
  }
}
