/**
 * Copyright (c) 2013, Federico Pérez Invidio. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 */

import java.util.List;

import edu.umflix.authenticationhandler.exceptions.InvalidTokenException;
import edu.umflix.exceptions.*;
import edu.umflix.model.*;
import edu.umflix.persistence.*;
import edu.umflix.authenticationhandler.AuthenticationHandler;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.validation.constraints.NotNull;

/**
 * {@inheritDoc}
 */
@Stateless
public class VendorManagerImpl implements VendorManager {
    @EJB(beanName = "AdDaoImpl")
    AdDao adDao;

    @EJB(beanName = "ClipDaoImpl")
    ClipDao clipDao;

    @EJB(beanName = "ClipDataDaoImpl")
    ClipDataDao clipDataDao;

    @EJB(beanName = "LicenseDaoImpl")
    LicenseDao licenseDao;

    @EJB(beanName = "MovieDaoImpl")
    MovieDao movieDao;

    @EJB(beanName = "AuthenticationHandlerImpl")
     AuthenticationHandler authenticationHandler;

    /**
     * {@inheritDoc}
     */
    @Override
    public void uploadMovie(@NotNull String userToken, @NotNull List<Role> roles, @NotNull Movie movie) throws InvalidTokenException {
        if (authenticationHandler.validateToken(userToken, roles)) {
            try {
                movieDao.getMovieById(movie.getId());
            } catch (MovieNotFoundException e) {
                if (movie.getClips() != null && movie.getLicenses() != null) {
                    movieDao.addMovie(movie);
                    for (License license : movie.getLicenses()) {
                        licenseDao.addLicense(license);
                    }
                    // Missing ClipDataStorage Service
                    for (Clip clip : movie.getClips()) {
                        clipDao.addClip(clip);
                    }
                }
            }
        } else {
            throw new InvalidTokenException();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void uploadClip(@NotNull String userToken, @NotNull List<Role> roles, @NotNull ClipData clipData) throws InvalidTokenException {
        if (authenticationHandler.validateToken(userToken, roles)) {
            // Missing ClipDataStorage Service
        } else {
            throw new InvalidTokenException();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void uploadAd(@NotNull String userToken, @NotNull List<Role> roles, @NotNull Ad advertisement) throws InvalidTokenException {
        if (authenticationHandler.validateToken(userToken, roles)) {
            try {
                adDao.getAdById(advertisement.getId());
            } catch (AdNotFoundException e) {
                adDao.addAd(advertisement);
                clipDao.addClip(advertisement.getClip());
                // Missing ClipDataStorage Service
            }
        } else {
            throw new InvalidTokenException();
        }
    }
}