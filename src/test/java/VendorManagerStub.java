import edu.um.umfix.vendormanager.impl.VendorManagerImpl;
import edu.umflix.authenticationhandler.AuthenticationHandler;
import edu.umflix.clipstorage.ClipStorage;
import edu.umflix.persistence.AdDao;
import edu.umflix.persistence.ClipDao;
import edu.umflix.persistence.LicenseDao;
import edu.umflix.persistence.MovieDao;

import javax.ejb.EJB;

/**
 * {@inheritDoc}
 */
public class VendorManagerStub extends VendorManagerImpl {

    public void setAdDao(AdDao adDao) {
        this.adDao = adDao;
    }

    public void setClipDao(ClipDao clipDao) {
        this.clipDao = clipDao;
    }

    public void setClipStorage(ClipStorage clipStorage) {
        this.clipStorage = clipStorage;
    }

    public void setLicenseDao(LicenseDao licenseDao) {
        this.licenseDao = licenseDao;
    }

    public void setMovieDao(MovieDao movieDao) {
        this.movieDao = movieDao;
    }

    public void setAuthenticationHandler(AuthenticationHandler authenticationHandler) {
        this.authenticationHandler = authenticationHandler;
    }
}
