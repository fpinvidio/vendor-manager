/**
 * Copyright (c) 2013, Federico Pérez Invidio. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 */

import edu.umflix.authenticationhandler.exceptions.InvalidTokenException;
import edu.umflix.clipstorage.ClipStorage;
import edu.umflix.exceptions.AdNotFoundException;
import edu.umflix.exceptions.MovieNotFoundException;
import edu.umflix.model.*;
import org.junit.Before;
import org.junit.Test;
import edu.umflix.authenticationhandler.AuthenticationHandler;
import edu.umflix.persistence.*;

import java.util.ArrayList;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * This class defines the tests to be performed.
 * @author Federico Pérez Invidio
 * @since 2013-05-28
 */
public class VendorManagerImplTest {
    private VendorManagerStub vendorManager;
    private RoleDao roleDao;
    private MovieDao movieDao;
    private AdDao adDao;
    private ClipStorage clipStorage;
    private AuthenticationHandler authenticationHandler;
    private Role adminRole;
    private Role userRole;
    private Role movieProvider;
    private Role adProvider;

    /**
     * Initializes all mock and stub classes.
     * Sets relevant parameters.
     *
     * @throws InvalidTokenException
     */
    @Before
    public void getMockManager() throws InvalidTokenException {

        //Prepare DAO mocks
        roleDao = mock(RoleDao.class);
        movieDao = mock(MovieDao.class);
        authenticationHandler = mock(AuthenticationHandler.class);
        adDao = mock(AdDao.class);
        clipStorage = mock(ClipStorage.class);

        //Prepare Roles mocks
        adminRole = mock(Role.class);
        when(adminRole.getId()).thenReturn((long) 1);
        userRole = mock(Role.class);
        when(userRole.getId()).thenReturn((long) 2);
        movieProvider = mock(Role.class);
        when(movieProvider.getId()).thenReturn((long) 3);
        adProvider = mock(Role.class);
        when(adProvider.getId()).thenReturn((long) 4);
        try {
            when(roleDao.getRoleById(Role.RoleType.ADMINISTRATOR.getRole())).thenReturn(adminRole);
            when(roleDao.getRoleById(Role.RoleType.USER.getRole())).thenReturn(userRole);
            when(roleDao.getRoleById(Role.RoleType.MOVIE_PROVIDER.getRole())).thenReturn(movieProvider);
            when(roleDao.getRoleById(Role.RoleType.AD_PROVIDER.getRole())).thenReturn(adProvider);

            when(authenticationHandler.isUserInRole("validTokenAdmin", roleDao.getRoleById(Role.RoleType.ADMINISTRATOR.getRole()))).thenReturn(true);
            when(authenticationHandler.isUserInRole("validTokenUser", roleDao.getRoleById(Role.RoleType.USER.getRole()))).thenReturn(true);
            when(authenticationHandler.isUserInRole("validTokenMovieProvider", roleDao.getRoleById(Role.RoleType.MOVIE_PROVIDER.getRole()))).thenReturn(true);
            when(authenticationHandler.isUserInRole("validTokenAdProvider", roleDao.getRoleById(Role.RoleType.AD_PROVIDER.getRole()))).thenReturn(true);

            when(adDao.getAdById((long) 1)).thenReturn(mock(Ad.class));
            when(adDao.getAdById((long) 2)).thenThrow(AdNotFoundException.class);
            when(movieDao.getMovieById((long) 1)).thenReturn(mock(Movie.class));
            when(movieDao.getMovieById((long) 2)).thenThrow(MovieNotFoundException.class);
        } catch (Exception e) {
            e.printStackTrace();
        }

        when(authenticationHandler.isUserInRole("validTokenAdmin", mock(Role.class))).thenReturn(false);
        when(authenticationHandler.isUserInRole("validTokenUser", mock(Role.class))).thenReturn(false);
        when(authenticationHandler.isUserInRole("validTokenMovieProvider", mock(Role.class))).thenReturn(false);
        when(authenticationHandler.isUserInRole("validTokenAdProvider", mock(Role.class))).thenReturn(false);

        //Prepare VendorManagerStub
        vendorManager = new VendorManagerStub();
        vendorManager.setAuthenticationHandler(authenticationHandler);
        vendorManager.setMovieDao(movieDao);
        vendorManager.setClipStorage(clipStorage);
        vendorManager.setAdDao(adDao);
    }

    /**
     * Tests the uploading of a movie with an invalid token.
     */
    @Test
    public void testInvalidToken() {
        String userToken = "invalidTokenMovieProvider";
        Movie movie = mock(Movie.class);
        try {
            vendorManager.uploadMovie(userToken, movieProvider, movie);
        } catch (InvalidTokenException e) {
            assertTrue(true);
        }
    }

    /**
     * Tests the uploading of a movie with an invalid Role.
     */
    @Test
    public void testInvalidRole() {
        String userToken = "validTokenUser";
        Movie movie = mock(Movie.class);
        try {
            vendorManager.uploadMovie(userToken, movieProvider, movie);
        } catch (InvalidTokenException e) {
            assertTrue(true);
        }
    }

    /**
     * Tests the uploading of a Movie, and verifies the call to movieDao.addMovie().
     */
    @Test
    public void testUploadMovie() throws InvalidTokenException {
        String userToken = "validTokenMovieProvider";
        Movie movie = mock(Movie.class);
        when(movie.getId()).thenReturn((long) 2);
        vendorManager.uploadMovie(userToken, movieProvider, movie);
        verify(movieDao).addMovie(movie);
    }

    /**
     * Tests the uploading of an existing Movie, and verifies that movieDao.addMovie()
     * is never called.
     */
    @Test
    public void testUploadExistingMovie() throws InvalidTokenException {
        String userToken = "validTokenMovieProvider";
        Movie movie = mock(Movie.class);
        when(movie.getId()).thenReturn((long) 1);
        vendorManager.uploadMovie(userToken, movieProvider, movie);
        verify(movieDao, times(0)).addMovie(movie);
    }

    /**
     * Tests the uploading of a ClipData with token and Role corresponding to a MovieProvider.
     */
    @Test
    public void testUploadClipMovie() throws InvalidTokenException {
        String userToken = "validTokenMovieProvider";
        ClipData clipData = mock(ClipData.class);
        when(clipData.getClip()).thenReturn(mock(Clip.class));
        vendorManager.uploadClip(userToken, movieProvider, clipData);
        verify(clipStorage).storeClipData(clipData);
    }

    /**
     * Tests the uploading of a ClipData with token and Role corresponding to an AdProvider.
     */
    @Test
    public void testUploadClipAd() throws InvalidTokenException {
        String userToken = "validTokenAdProvider";
        ClipData clipData = mock(ClipData.class);
        when(clipData.getClip()).thenReturn(mock(Clip.class));
        vendorManager.uploadClip(userToken, adProvider, clipData);
        verify(clipStorage).storeClipData(clipData);
    }

    /**
     * Tests the uploading of an Ad, and verifies the call to adDao.addAd().
     */
    @Test
    public void testUploadAd() throws InvalidTokenException {
        String userToken = "validTokenAdProvider";
        Ad ad = mock(Ad.class);
        when(ad.getId()).thenReturn((long) 2);
        vendorManager.uploadAd(userToken, adProvider, ad);
        verify(adDao).addAd(ad);
    }

    /**
     * Tests the uploading of an existing Ad, and verifies that adDao.addAd()
     * is never called.
     */
    @Test
    public void testUploadExistingAd() throws InvalidTokenException {
        String userToken = "validTokenAdProvider";
        Ad ad = mock(Ad.class);
        when(ad.getId()).thenReturn((long) 1);
        vendorManager.uploadAd(userToken, adProvider, ad);
        verify(adDao, times(0)).addAd(ad);
    }

}

