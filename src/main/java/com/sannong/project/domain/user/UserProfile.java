package com.sannong.project.domain.user;

import com.sannong.project.domain.region.AddressRegion;

/**
 * Created by Bright Huang on 2/9/15.
 */
public class UserProfile {
    private User user;
    private AddressRegion addressRegion;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public AddressRegion getAddressRegion() {
        return addressRegion;
    }

    public void setAddressRegion(AddressRegion addressRegion) {
        this.addressRegion = addressRegion;
    }
}
