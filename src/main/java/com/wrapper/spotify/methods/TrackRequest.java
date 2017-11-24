package com.wrapper.spotify.methods;

import com.google.common.util.concurrent.SettableFuture;
import com.wrapper.spotify.JsonUtil;
import com.wrapper.spotify.exceptions.*;
import com.wrapper.spotify.models.Track;

import java.io.IOException;

public class TrackRequest extends AbstractRequest {

  public TrackRequest(Builder builder) {
    super(builder);
  }

  public static Builder builder() {
    return new Builder();
  }

  public SettableFuture<Track> getAsync() {
    final SettableFuture<Track> trackFuture = SettableFuture.create();

    try {
      trackFuture.set(JsonUtil.createTrack(getJson()));
    } catch (Exception e) {
      trackFuture.setException(e);
    }

    return trackFuture;
  }

  public Track get() throws
          IOException,
          NoContentException,
          BadRequestException,
          UnauthorizedException,
          ForbiddenException,
          NotFoundException,
          TooManyRequestsException,
          InternalServerErrorException,
          BadGatewayException,
          ServiceUnavailableException {
    return JsonUtil.createTrack(getJson());
  }

  public static final class Builder extends AbstractRequest.Builder<Builder> {

    /**
     * The track with the given id.
     *
     * @param id The id for the track.
     * @return Track Request
     */
    public Builder id(String id) {
      assert (id != null);
      return setPath(String.format("/v1/tracks/%s", id));
    }

    public TrackRequest build() {
      return new TrackRequest(this);
    }

  }

}
