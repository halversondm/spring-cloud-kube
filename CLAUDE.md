# CLAUDE.md

## Build and test
- `mvn -B clean verify` from the repo root builds all modules, runs tests, and enforces a JaCoCo 70% instruction coverage check (excluding `*Application`).
- Java 21, Spring Boot parent and Spring Cloud BOM versions are set in the root `pom.xml`.

## Layout
- Maven multi-module: `book`, `gateway` (Spring Cloud Gateway, WebFlux), `account`, `customer` (OpenFeign client).
- Each module has a `Dockerfile` and `kube/` manifests; `database/` and `kube/` at the root hold shared Kubernetes/DB config.

## Conventions
- Controller tests use `@WebMvcTest` with `@MockitoBean`.
- Keep changes minimal and match existing style.
