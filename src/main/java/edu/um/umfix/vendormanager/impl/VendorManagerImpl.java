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
import org.apache.log4j.Logger;

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
    static Logger logger = Logger.getLogger(VendorManager.class);

    @EJB(beanName = "AdDao")
    protected AdDao adDao;

    @EJB(beanName = "ClipDao")
    protected ClipDao clipDao;

    @EJB(beanName = "ClipStorage")
    protected ClipStorage clipStorage;

    @EJB(beanName = "LicenseDao")
    protected LicenseDao licenseDao;

    @EJB(beanName = "MovieDao")
    protected MovieDao movieDao;

    @EJB(beanName = "AuthenticationHandler")
    protected AuthenticationHandler authenticationHandler;

    /**
     * {@inheritDoc}
     */
    @Override
    public void uploadMovie(@NotNull String userToken, @NotNull Role role, @NotNull Movie movie) throws InvalidTokenException {
        if (authenticationHandler.isUserInRole(userToken, role)) {
            try {
                movieDao.getMovieById(movie.getId());
                logger.info("found duplicate for movie, skipping add");
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
                logger.info("verified for duplicates, proceeding to add movie");
                movieDao.addMovie(movie);
            }
        } else {
            logger.info("invalid token received or user has no privileges");
            throw new InvalidTokenException();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void uploadClip(@NotNull String userToken, @NotNull Role role, @NotNull ClipData clipData) throws InvalidTokenException {
        if (authenticationHandler.isUserInRole(userToken, role)) {
            logger.info("checking for valid clip data");
            if (clipData.getClip() != null) {
                logger.info("proceeding to store clip data");
                clipStorage.storeClipData(clipData);
            }
        } else {
            logger.info("invalid token received or user has no privileges");
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
                logger.info("found duplicate for ad, skipping add");
            } catch (AdNotFoundException e) {
                logger.info("verified for duplicates, proceeding to add ad");
                adDao.addAd(advertisement);
                //clipDao.addClip(advertisement.getClip());
            }
        } else {
            logger.info("invalid token received or user has no privileges");
            throw new InvalidTokenException();
        }
    }
}
