/**
 * Copyright (c) 2013, Federico Pérez Invidio. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 */

package edu.um.umfix.vendormanager.impl;

import edu.um.umfix.vendormanager.VendorManager;
import edu.umflix.authenticationhandler.exceptions.InvalidTokenException;
import edu.umflix.authenticationhandler.AuthenticationHandler;
import edu.umflix.clipstorage.ClipStorage;
import edu.umflix.exceptions.*;
import edu.umflix.model.*;
import edu.umflix.persistence.*;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.jws.WebService;
import javax.validation.constraints.NotNull;

/**
 * {@inheritDoc}
 */
@WebService(portName = "VendorManagerPort", serviceName = "VendorManagerWebService", targetNamespace = "http://um.org/wsdl")
@Stateless(name = "VendorManager")
public class VendorManagerImpl implements VendorManager {
    @EJB(beanName = "AdDao")
    protected AdDao adDao;

    @EJB(beanName = "ClipDao")
    protected ClipDao clipDao;

    @EJB(beanName = "clipstorage")
    protected ClipStorage clipStorage;

    @EJB(beanName = "LicenseDao")
    protected LicenseDao licenseDao;

    @EJB(beanName = "MovieDao")
    protected MovieDao movieDao;

    @EJB(beanName = "AuthenticationHandlerImpl")
    protected AuthenticationHandler authenticationHandler;

    /**
     * {@inheritDoc}
     */
    @Override
    public void uploadMovie(@NotNull String userToken, @NotNull Role role, @NotNull Movie movie) throws InvalidTokenException {
        if (authenticationHandler.isUserInRole(userToken, role)) {
            try {
                movieDao.getMovieById(movie.getId());
            } catch (MovieNotFoundException e) {
                /*if (movie.getClips() != null && movie.getLicenses() != null) {
                    movieDao.addMovie(movie);
                    for (License license : movie.getLicenses()) {
                        licenseDao.addLicense(license);
                    }
                    clipStorage.storeClipData(null);
                    for (Clip clip : movie.getClips()) {
                        clipDao.addClip(clip);
                    }
                }*/
                movieDao.addMovie(movie);
            }
        } else {
            throw new InvalidTokenException();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void uploadClip(@NotNull String userToken, @NotNull Role role, @NotNull ClipData clipData) throws InvalidTokenException {
        if (authenticationHandler.isUserInRole(userToken, role)) {
            if (clipData.getClip() != null) {
                clipStorage.storeClipData(clipData);
            }
        } else {
            throw new InvalidTokenException();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void uploadAd(@NotNull String userToken, @NotNull Role role, @NotNull Ad advertisement) throws InvalidTokenException {
        if (authenticationHandler.isUserInRole(userToken, role)) {
            try {
                adDao.getAdById(advertisement.getId());
            } catch (AdNotFoundException e) {
                adDao.addAd(advertisement);
                //clipDao.addClip(advertisement.getClip());
            }
        } else {
            throw new InvalidTokenException();
        }
    }
}
