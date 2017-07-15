package interfaces;


public abstract class PermissionCallback {
    /**
     *  Called when runtime permission is granted by user
     */
    public abstract void onPermissionGranted();
    /**
     *  Called when runtime permission is denied by user
     */
    public abstract void onPermissionDenied();
}
