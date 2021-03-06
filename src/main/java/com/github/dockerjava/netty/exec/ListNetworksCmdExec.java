package com.github.dockerjava.netty.exec;

import com.fasterxml.jackson.core.type.TypeReference;
import com.github.dockerjava.api.command.ListNetworksCmd;
import com.github.dockerjava.api.model.Network;
import com.github.dockerjava.core.DockerClientConfig;
import com.github.dockerjava.netty.MediaType;
import com.github.dockerjava.netty.WebTarget;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import static com.google.common.net.UrlEscapers.urlPathSegmentEscaper;

public class ListNetworksCmdExec extends AbstrSyncDockerCmdExec<ListNetworksCmd, List<Network>> implements
        ListNetworksCmd.Exec {

    private static final Logger LOGGER = LoggerFactory.getLogger(ListNetworksCmdExec.class);

    public ListNetworksCmdExec(WebTarget baseResource, DockerClientConfig dockerClientConfig) {
        super(baseResource, dockerClientConfig);
    }

    @Override
    protected List<Network> execute(ListNetworksCmd command) {
        WebTarget webTarget = getBaseResource().path("/networks");

        if (command.getFilters() != null)
            webTarget = webTarget.queryParam("filters", urlPathSegmentEscaper().escape(command.getFilters().toString()));

        LOGGER.trace("GET: {}", webTarget);

        return webTarget.request().accept(MediaType.APPLICATION_JSON).get(new TypeReference<List<Network>>() {
        });
    }
}
