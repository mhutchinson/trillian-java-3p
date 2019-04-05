load("@bazel_tools//tools/build_defs/repo:http.bzl", "http_archive")
load("@bazel_tools//tools/build_defs/repo:git.bzl", "git_repository")

http_archive(
    name = "build_stack_rules_proto",
    sha256 = "5474d1b83e24ec1a6db371033a27ff7aff412f2b23abba86fedd902330b61ee6",
    strip_prefix = "rules_proto-91cbae9bd71a9c51406014b8b3c931652fb6e660",
    urls = ["https://github.com/stackb/rules_proto/archive/91cbae9bd71a9c51406014b8b3c931652fb6e660.tar.gz"],
)

load("@build_stack_rules_proto//java:deps.bzl", "io_grpc_grpc_java", "java_proto_compile", "java_grpc_compile")

io_grpc_grpc_java()

load("@io_grpc_grpc_java//:repositories.bzl", "grpc_java_repositories")

grpc_java_repositories(omit_com_google_protobuf = True)

load("@build_stack_rules_proto//java:deps.bzl", "java_grpc_library")

java_grpc_library()

load("@build_stack_rules_proto//java:deps.bzl", "java_proto_compile")

java_proto_compile()

git_repository(
    name = "trillian",
    branch = "bazel_fun",
    remote = "https://github.com/mhutchinson/trillian.git",
)

git_repository(
    name = "googleapi",
    branch = "dropload",
    remote = "https://github.com/mhutchinson/googleapis.git",
)

# TODO(mhutchinson): Determine why Go libraries are required
http_archive(
    name = "io_bazel_rules_go",
    strip_prefix = "rules_go-7d17d496a6b32f6a573c6c22e29c58204eddf3d4",
    urls = ["https://github.com/bazelbuild/rules_go/archive/7d17d496a6b32f6a573c6c22e29c58204eddf3d4.zip"],
)
