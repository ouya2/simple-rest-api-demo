package com.acme.utils;

import static com.acme.model.Customer.ADDRESSES_PROPERTY;
import static com.acme.model.Customer.ID_PROPERTY;

import com.acme.model.Address;
import com.acme.model.Customer;
import java.beans.FeatureDescriptor;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.util.CollectionUtils;

/**
 * Utility class for the bean object.
 */
public class BeanUtils {

  private static final Logger LOG = LoggerFactory.getLogger(BeanUtils.class);

  /**
   * Copy the properties of source customer to target customer
   *
   * @param source {@link Customer}
   * @param target {@link Customer}
   */
  public static void copyCustomerProperties(Customer source, Customer target) {
    org.springframework.beans.BeanUtils.copyProperties(source, target,
        BeanUtils.getIgnoreCustomerPropertyNames(source));
    // Copy address separately
    BeanUtils.copyAddresses(source.getAddresses(), target.getAddresses());
  }

  /**
   * Return the customer properties names to ignore.
   *
   * @param source
   * @return
   */
  public static String[] getIgnoreCustomerPropertyNames(Customer source) {
    if (source == null) {
      LOG.error("Found null customer when calculating ignore properties");
      throw new RuntimeException("Property to ignore cannot be found for null customer.");
    }
    List<String> propertiesToIgnore = new ArrayList<>(Arrays.asList(ID_PROPERTY, ADDRESSES_PROPERTY));
    propertiesToIgnore.addAll(Arrays.asList(getNullPropertyNames(source)));
    return propertiesToIgnore.toArray(new String[0]);
  }

  /**
   * Copy the source addresses to the target addresses.
   *
   * @param sources Set of {@link Address}
   * @param targets Set of {@link Address}
   */
  public static void copyAddresses(Set<Address> sources, Set<Address> targets) {
    if (CollectionUtils.isEmpty(sources)) {
      return;
    }

    if (CollectionUtils.isEmpty(targets)) {
      LOG.error("Target address is empty");
      throw new RuntimeException("Target addresses cannot be updated since it is empty.");
    }

    Map<Long, Address> idAddressMap = targets.stream().collect(Collectors.toMap(Address::getId,
        Function.identity()));

    for (Address address : sources) {
      Address targetAddress = idAddressMap.get(address.getId());
      if (targetAddress != null) {
        org.springframework.beans.BeanUtils.copyProperties(address, targetAddress, Address.ID_PROPERTY);
      }
      else {
        LOG.error("Cannot find the target address from source address id :" + address.getId());
        throw new RuntimeException("Target address can not be updated for " + address.toString());
      }
    }
  }

  /**
   * Get the null value properties excluding id and addresses.
   *
   * @param source
   * @return
   */
  private static String[] getNullPropertyNames(Customer source) {
    final BeanWrapper wrappedSource = new BeanWrapperImpl(source);
    return Stream.of(wrappedSource.getPropertyDescriptors())
        .map(FeatureDescriptor::getName)
        .filter(propertyName -> !propertyName.equals(ID_PROPERTY) &&
            !propertyName.equals(ADDRESSES_PROPERTY) &&
            wrappedSource.getPropertyValue(propertyName) == null)
        .toArray(String[]::new);
  }
}
