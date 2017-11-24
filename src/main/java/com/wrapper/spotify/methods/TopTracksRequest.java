package com.wrapper.spotify.methods;

import com.google.common.util.concurrent.SettableFuture;
import com.wrapper.spotify.JsonUtil;
import com.wrapper.spotify.exceptions.*;
import com.wrapper.spotify.models.Track;
import net.sf.json.JSONObject;

import java.io.IOException;
import java.util.List;

public class TopTracksRequest extends AbstractRequest {

  public TopTracksRequest(Builder builder) {
    super(builder);
  }

  public static Builder builder() {
    return new Builder();
  }

  public SettableFuture<List<Track>> getAsync() {
    final SettableFuture<List<Track>> tracksFuture = SettableFuture.create();

    try {
      final JSONObject jsonObject = JSONObject.fromObject(getJson());
      tracksFuture.set(JsonUtil.createTracks(jsonObject));
    } catch (Exception e) {
      tracksFuture.setException(e);
    }

    return tracksFuture;
  }

  public List<Track> get() throws
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
    final JSONObject jsonObject = JSONObject.fromObject(getJson());
    return JsonUtil.createTracks(jsonObject);
  }

  public static final class Builder extends AbstractRequest.Builder<Builder> {

    public Builder id(String id) {
      assert (id != null);
      return setPath(String.format("/v1/artists/%s/toptracks", id));
    }

    public Builder countryCode(String countryCode) {
      assert (countryCode != null);
      return setParameter("country", countryCode);
    }

    public TopTracksRequest build() {
      return new TopTracksRequest(this);
    }

  }

}
