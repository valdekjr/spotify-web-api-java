package com.wrapper.spotify.methods;

import com.google.common.util.concurrent.SettableFuture;
import com.wrapper.spotify.JsonUtil;
import com.wrapper.spotify.exceptions.*;
import com.wrapper.spotify.models.SnapshotResult;

import java.io.IOException;

public class AddTrackToPlaylistRequest extends AbstractRequest {

  public AddTrackToPlaylistRequest(Builder builder) {
    super(builder);
  }

  public static Builder builder() {
    return new Builder();
  }

  public SettableFuture<SnapshotResult> getAsync() {
    final SettableFuture<SnapshotResult> addTrackFuture = SettableFuture.create();

    try {
      addTrackFuture.set(JsonUtil.createSnapshotResult(postJson()));
    } catch (Exception e) {
      addTrackFuture.setException(e);
    }

    return addTrackFuture;
  }

  public SnapshotResult get() throws
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
    return JsonUtil.createSnapshotResult(postJson());
  }

  public static final class Builder extends AbstractRequest.Builder<Builder> {

    public Builder position(int position) {
      assert (position >= 0);
      return setParameter("position", String.valueOf(position));
    }

    public AddTrackToPlaylistRequest build() {
      setHeaderParameter("Content-Type", "application/json");
      return new AddTrackToPlaylistRequest(this);
    }

  }

}