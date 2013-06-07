/**
 * Copyright (c) 2013, Federico Pérez Invidio. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 */

import edu.umflix.authenticationhandler.exceptions.InvalidTokenException;
import org.junit.Before;
import org.junit.Test;
import edu.umflix.authenticationhandler.AuthenticationHandler;
import edu.umflix.persistence.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class VendorManagerImplTest {

    /*private VendorManagerImpl vendorManager;
    private RoleDao roleDao;
    private ActivityDao activityDao;
    private MovieDao movieDao;
    private AuthenticationHandler authenticationHandler;
    private AdDao adDao;

    @Before
    public void getMockManager() throws InvalidTokenException {
        roleDao = mock(RoleDao.class);
        activityDao = mock(ActivityDao.class);
        movieDao = mock(MovieDao.class);
        authenticationHandler = mock(AuthenticationHandler.class);
        adDao = mock(AdDao.class);

        //prepare mock roleDao
        Role adminRole = mock(Role.class);
        when(adminRole.getId()).thenReturn((long) 1);
        Role userRole = mock(Role.class);
        when(userRole.getId()).thenReturn((long) 2);
        Role movieProvider = mock(Role.class);
        when(movieProvider.getId()).thenReturn((long) 3);
        Role adProvider = mock(Role.class);
        when(adProvider.getId()).thenReturn((long) 4);
        Role reviewer = mock(Role.class);
        when(reviewer.getId()).thenReturn((long) 5);
        when(roleDao.getRoleById(Role.RoleType.ADMINISTRATOR.getRole())).thenReturn(adminRole);
        when(roleDao.getRoleById(Role.RoleType.USER.getRole())).thenReturn(userRole);
        when(roleDao.getRoleById(Role.RoleType.MOVIE_PROVIDER.getRole())).thenReturn(movieProvider);
        when(roleDao.getRoleById(Role.RoleType.REVIEWER.getRole())).thenReturn(reviewer);
        when(roleDao.getRoleById(Role.RoleType.AD_PROVIDER.getRole())).thenReturn(adProvider);

        //prepare mock movieDao
        Movie enabledMovie = mock(Movie.class);
        Movie notEnabledMovie = mock(Movie.class);
        when(enabledMovie.isEnabled()).thenReturn(true);
        when(notEnabledMovie.isEnabled()).thenReturn(false);
        when(enabledMovie.getClips()).thenReturn(new ArrayList<Clip>());
        when(notEnabledMovie.getClips()).thenReturn(new ArrayList<Clip>());
        when(movieDao.getMovieById((long) 1)).thenReturn(enabledMovie);
        when(movieDao.getMovieById((long) 2)).thenReturn(notEnabledMovie);
        when(movieDao.getMovieById((long) 3)).thenThrow(MovieNotFoundException.class);

        //prepare mock authenticationHandler
        when(authenticationHandler.validateToken("validTokenAdmin")).thenReturn(true);
        when(authenticationHandler.validateToken("validTokenUser")).thenReturn(true);
        when(authenticationHandler.validateToken("validTokenMovieProvider")).thenReturn(true);
        when(authenticationHandler.validateToken("validTokenAdProvider")).thenReturn(true);
        when(authenticationHandler.validateToken("validTokenReviewer")).thenReturn(true);
        when(authenticationHandler.validateToken("expiredToken")).thenReturn(false);
        when(authenticationHandler.validateToken("invalidToken")).thenThrow(InvalidTokenException.class);

        when(authenticationHandler.isUserInRole("validTokenAdmin", mock(Role.class))).thenReturn(false);
        when(authenticationHandler.isUserInRole("validTokenAdmin", roleDao.getRoleById(Role.RoleType.ADMINISTRATOR.getRole()))).thenReturn(true);
        when(authenticationHandler.isUserInRole("validTokenUser", mock(Role.class))).thenReturn(false);
        when(authenticationHandler.isUserInRole("validTokenUser", roleDao.getRoleById(Role.RoleType.USER.getRole()))).thenReturn(true);
        when(authenticationHandler.isUserInRole("validTokenMovieProvider", mock(Role.class))).thenReturn(false);
        when(authenticationHandler.isUserInRole("validTokenMovieProvider", roleDao.getRoleById(Role.RoleType.MOVIE_PROVIDER.getRole()))).thenReturn(true);
        when(authenticationHandler.isUserInRole("validTokenAdProvider", mock(Role.class))).thenReturn(false);
        when(authenticationHandler.isUserInRole("validTokenAdProvider", roleDao.getRoleById(Role.RoleType.AD_PROVIDER.getRole()))).thenReturn(true);
        when(authenticationHandler.isUserInRole("validTokenReviewer", mock(Role.class))).thenReturn(false);
        when(authenticationHandler.isUserInRole("validTokenReviewer", roleDao.getRoleById(Role.RoleType.REVIEWER.getRole()))).thenReturn(true);

        User user = mock(User.class);
        User admin = mock(User.class);
        when(user.getEmail()).thenReturn("user@mail.com");
        when(admin.getEmail()).thenReturn("admin@mail.com");
        when(authenticationHandler.getUserOfToken("validTokenUser")).thenReturn(user);
        when(authenticationHandler.getUserOfToken("validTokenAdmin")).thenReturn(admin);

        //prepare mock clipStorage
        when(clipStorage.getClipDataByClipId((long) 1)).thenReturn(mock(ClipData.class));
        when(clipStorage.getClipDataByClipId((long) 2)).thenThrow(FileNotFoundException.class);

        movieManager = new MovieManagerImpl();
        movieManager.setActivityDao(activityDao);
        movieManager.setAuthenticationHandler(authenticationHandler);
        movieManager.setRoleDao(roleDao);
        movieManager.setMovieDao(movieDao);
        movieManager.setClipStorage(clipStorage);
        movieManager.setAdDao(adDao);
    }   */

    @Test
    public void testValidToken() throws InvalidTokenException {
        String userToken = "validTokenUser";
        //assertArrayEquals(((ArrayList<Clip>) movieManager.getMovie(userToken, (long) 1)).toArray(), new ArrayList<Clip>().toArray());
    }

    @Test
    public void testInvalidToken() throws InvalidTokenException {
        String userToken = "validTokenUser";
        //assertArrayEquals(((ArrayList<Clip>) movieManager.getMovie(userToken, (long) 1)).toArray(), new ArrayList<Clip>().toArray());
    }

    @Test
    public void testValidRole() throws InvalidTokenException {
        String userToken = "validTokenUser";
        //assertArrayEquals(((ArrayList<Clip>) movieManager.getMovie(userToken, (long) 1)).toArray(), new ArrayList<Clip>().toArray());
    }

    @Test
    public void testInvalidRole() throws InvalidTokenException {
        String userToken = "validTokenUser";
        //assertArrayEquals(((ArrayList<Clip>) movieManager.getMovie(userToken, (long) 1)).toArray(), new ArrayList<Clip>().toArray());
    }

    @Test
    public void testUploadMovie() throws InvalidTokenException {
        String userToken = "validTokenUser";
        //assertArrayEquals(((ArrayList<Clip>) movieManager.getMovie(userToken, (long) 1)).toArray(), new ArrayList<Clip>().toArray());
    }

    @Test
    public void testUploadClip() throws InvalidTokenException {
        String userToken = "validTokenUser";
        //assertArrayEquals(((ArrayList<Clip>) movieManager.getMovie(userToken, (long) 1)).toArray(), new ArrayList<Clip>().toArray());
    }

    @Test
    public void testUploadAd() throws InvalidTokenException {
        String userToken = "validTokenUser";
        //assertArrayEquals(((ArrayList<Clip>) movieManager.getMovie(userToken, (long) 1)).toArray(), new ArrayList<Clip>().toArray());
    }

}

