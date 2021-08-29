package web23.web18.service;

import org.springframework.stereotype.Component;
import web23.web18.model.MapperProfile;

@Component
public class ServiceProfile {
    private MapperProfile mapperProfile;

    ServiceProfile(MapperProfile mapperProfile) {
        this.mapperProfile = mapperProfile;
    }

    public void add(String avator, int userId) {
        this.mapperProfile.add(avator, userId);
    }

}
