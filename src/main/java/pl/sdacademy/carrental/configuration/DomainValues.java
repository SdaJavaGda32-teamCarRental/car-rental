package pl.sdacademy.carrental.configuration;

import org.springframework.stereotype.Component;

@Component
// TODO: 18/06/2020 it would probably be better to store these values in DB
public class DomainValues {
   public static final int UPCHARGE_FOR_CHANGE_OF_BRANCH = 50;
   public static final int MINIMUM_CARS_IN_BRANCH = 3;
   
}
