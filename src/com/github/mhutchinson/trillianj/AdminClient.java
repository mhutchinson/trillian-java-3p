package com.github.mhutchinson.trillianj;

import com.google.trillian.proto.ListTreesRequest;
import com.google.trillian.proto.ListTreesResponse;
import com.google.trillian.proto.Tree;
import com.google.trillian.proto.TrillianAdminGrpc;
import com.google.trillian.proto.TrillianAdminGrpc.TrillianAdminBlockingStub;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import java.util.ArrayList;
import java.util.List;

/**
 * A Java client for accessing the Trillian Admin API.
 */
public class AdminClient {

  private final ManagedChannel channel;
  private final TrillianAdminBlockingStub blockingStub;

  public AdminClient(String host, int port) {
    this(ManagedChannelBuilder.forAddress(host, port).usePlaintext());
  }

  /** Construct client for accessing AdminClient server using the existing channel. */
  public AdminClient(ManagedChannelBuilder<?> channelBuilder) {
    channel = channelBuilder.build();
    blockingStub = TrillianAdminGrpc.newBlockingStub(channel);
  }

  public List<Long> getTreeIds() {
    ListTreesRequest request = ListTreesRequest.newBuilder().build();

    ListTreesResponse trees;
    trees = blockingStub.listTrees(request);

    List<Long> result = new ArrayList<>(trees.getTreeCount());
    for (Tree t: trees.getTreeList()) {
      result.add(t.getTreeId());
    }
    return result;
  }

  /**
   * Preconditions:
   * <ol>
   *   <li>Deploy Trillian as per https://github.com/google/trillian/tree/master/examples/deployment/kubernetes</li>
   *   <li>kubectl port-forward service/trillian-log-service 35791:8090</li>
   * </ol>
   */
  public static void main(String[] args) {
    AdminClient client = new AdminClient("localhost", 35791);
    List<Long> trees = client.getTreeIds();
    System.out.println("Oh hi. Total trees: " + trees.size());
    for (Long id: trees) {
      System.out.println(id);
    }
  }
}
