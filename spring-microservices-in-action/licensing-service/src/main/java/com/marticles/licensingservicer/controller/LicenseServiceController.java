package com.marticles.licensingservicer.controller;

import com.marticles.licensingservicer.model.License;
import com.marticles.licensingservicer.service.LicenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.sleuth.Sampler;
import org.springframework.cloud.sleuth.sampler.AlwaysSampler;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value="v1/organizations/{organizationId}/licenses")
public class LicenseServiceController {
    @Autowired
    private LicenseService licenseService;


    @RequestMapping(value="/{licenseId}",method = RequestMethod.GET)
    public License getLicenses(@PathVariable("organizationId") String organizationId,
                               @PathVariable("licenseId") String licenseId) {

        return new License()
                .withId(licenseId)
                .withOrganizationId(organizationId)
                .withProductName("Test1")
                .withLicenseType("Test2");
    }

    @RequestMapping(value="/{licenseId}/{clientType}",method = RequestMethod.GET)
    public License getLicensesWithClient(
            @PathVariable("organizationId") String organizationId,
            @PathVariable("licenseId") String licenseId,
            @PathVariable("clientType") String clientType) {

        return new License()
                .withId(licenseId)
                .withOrganizationId(organizationId)
                .withProductName("Test1")
                .withLicenseType(clientType);
    }

    @RequestMapping(value="{licenseId}",method = RequestMethod.PUT)
    public String updateLicenses( @PathVariable("licenseId") String licenseId) {
        return String.format("This is the put");
    }

    @RequestMapping(value="{licenseId}",method = RequestMethod.POST)
    public String saveLicenses( @PathVariable("licenseId") String licenseId) {
        return String.format("This is the post");
    }

    @RequestMapping(value="{licenseId}",method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public String deleteLicenses( @PathVariable("licenseId") String licenseId) {
        return String.format("This is the Delete");
    }
}
