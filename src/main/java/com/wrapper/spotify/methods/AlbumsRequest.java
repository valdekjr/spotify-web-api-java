package com.wrapper.spotify.methods;

import com.google.common.base.Joiner;
import com.google.common.util.concurrent.SettableFuture;
import com.wrapper.spotify.JsonUtil;
import com.wrapper.spotify.exceptions.*;
import com.wrapper.spotify.models.Album;

import java.io.IOException;
import java.util.List;

public class AlbumsRequest extends AbstractRequest {

  public AlbumsRequest(Builder builder) {
    super(builder);
  }

  public static Builder builder() {
    return new Builder();
  }

  public SettableFuture<List<Album>> getAsync() {
    final SettableFuture<List<Album>> albumsFuture = SettableFuture.create();

    try {
      albumsFuture.set(JsonUtil.createAlbums(getJson()));
    } catch (Exception e) {
      albumsFuture.setException(e);
    }

    return albumsFuture;
  }

  public List<Album> get() throws
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
    return JsonUtil.createAlbums(getJson());
  }

  public static final class Builder extends AbstractRequest.Builder<Builder> {

    public Builder id(List<String> ids) {
      assert (ids != null);
      String idsParameter = Joiner.on(",").join(ids);
      setPath("/v1/albums");
      return setParameter("ids", idsParameter);
    }

    public AlbumsRequest build() {
      return new AlbumsRequest(this);
    }

  }
}
