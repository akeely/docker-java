package com.github.dockerjava.api.command;

import com.github.dockerjava.api.model.Network;
import com.github.dockerjava.core.RemoteApiVersion;

import javax.annotation.CheckForNull;
import java.util.Map;

/**
 * Create a network.
 *
 * @since {@link RemoteApiVersion#VERSION_1_21}
 */
public interface CreateNetworkCmd extends SyncDockerCmd<CreateNetworkResponse> {

    @CheckForNull
    public String getName();

    @CheckForNull
    public String getDriver();

    @CheckForNull
    public Network.Ipam getIpam();

    /** The new network's name. Required. */
    public CreateNetworkCmd withName(String name);

    /** Name of the network driver to use. Defaults to <code>bridge</code>. */
    public CreateNetworkCmd withDriver(String driver);

    /** Optional custom IP scheme for the network. */
    public CreateNetworkCmd withIpamConfig(Map<String, String> config);

    public static interface Exec extends DockerCmdSyncExec<CreateNetworkCmd, CreateNetworkResponse> {
    }
}