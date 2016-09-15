package com.andromed.codes.universalresourcehandler.callbacks;

/**
 * Created by Utilisateur on 11/09/2016.
 */
public interface CallbackInterface {

    /**
     * Callback on success to communicate data back to the main UI thread when the process is
     * successful
     * @param result specifies the result of the process : you may have to cast this based on
     *               your result requirements.
     */
    void onSuccess(Object result);

    /**
     * Callback on failure to handle any error during the task execution
     */
    void onFailure();
}
