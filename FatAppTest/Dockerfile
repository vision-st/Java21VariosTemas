# -----------------------
# Etapa 1: Build y jlink
# -----------------------
FROM openjdk:21-jdk as build

# Variables
ENV JAR_NAME=FatAppTest-1.0-SNAPSHOT.jar
ENV APP_DIR=/app
ENV RUNTIME_DIR=/app/custom-runtime

# Crear directorio de trabajo
WORKDIR $APP_DIR

# Copiar el JAR
COPY target/$JAR_NAME .

# Usar jdeps para extraer módulos necesarios
RUN jdeps \
      --multi-release 21 \
      --print-module-deps \
      --ignore-missing-deps \
      $JAR_NAME > modules.txt

# Crear runtime reducido con jlink
RUN jlink \
      --add-modules $(cat modules.txt),jdk.crypto.ec \
      --output $RUNTIME_DIR \
      --strip-debug \
      --compress=2 \
      --no-header-files \
      --no-man-pages

# -----------------------
# Etapa 2: Imagen final
# -----------------------
FROM alpine:3.20

# Instalar dependencias mínimas (glibc/stdlib)
RUN apk add --no-cache libstdc++ libc6-compat

# Variables
ENV APP_DIR=/app
WORKDIR $APP_DIR

# Copiar runtime optimizado y JAR desde la etapa anterior
COPY --from=build /app/custom-runtime ./custom-runtime
COPY --from=build /app/FatAppTest-1.0-SNAPSHOT.jar .

# Establece el entrypoint
ENTRYPOINT ["./custom-runtime/bin/java", "-jar", "FatAppTest-1.0-SNAPSHOT.jar"]
