/**
 * Copyright (c) 2013, Federico Pérez Invidio. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 */

package edu.um.umfix.vendormanager;

import edu.umflix.authenticationhandler.exceptions.InvalidTokenException;
import edu.umflix.model.*;

import javax.ejb.Local;
import javax.jws.WebService;
import java.util.List;

/**
 * This interface defines the methods related to the uploading of movies, clips and ads.
 * @author Federico Pérez Invidio
 * @since 2013-05-28
 * @see edu.umflix.model.Ad
 * @see edu.umflix.model.ClipData
 * @see edu.umflix.model.License
 * @see edu.umflix.model.Movie
 */
@Local
public interface VendorManager {
    /**
     * Uploads movie and corresponding licenses, clips and clipData.
     * Movie must not exist on db and it has to contain relevant data for it to be stored.
     *
     * <p>In order to upload movie, a userToken along with the role needs to be provided.</p>
     *
     * @param userToken - the token of the uploading user.
     * @param role - the role of the uploading user.
     * @param movie - the movie object to be uploaded.
     * @throws InvalidTokenException
     */

    public void uploadMovie(String userToken, Role role, Movie movie) throws InvalidTokenException;

    /**
     * Uploads clipData and corresponding clip.
     *
     * <p>In order to upload clipData, a clip along with the role needs to be provided.</p>
     *
     * @param userToken - the token of the uploading user.
     * @param role - the role of the uploading user.
     * @param clipData - the clipData object to be uploaded
     * @throws InvalidTokenException
     */
    public void uploadClip(String userToken, Role role,ClipData clipData) throws InvalidTokenException;

    /**
     * Uploads Ad and corresponding clip and clipData.
     * Ad must not exist on db and it has to contain relevant data for it to be stored.
     *
     * <p>In order to upload movie, a userToken along with the role needs to be provided.</p>
     *
     * @param userToken - the token of the uploading user.
     * @param role - the role of the uploading user.
     * @param advertisement - the ad object to be uploaded
     * @throws InvalidTokenException
     */
    public void uploadAd(String userToken, Role role,Ad advertisement) throws InvalidTokenException;
}
