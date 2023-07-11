# Stage 1: Build Grafana
FROM grafana/grafana AS grafana

# Stage 2: Build Prometheus
FROM prom/prometheus AS prometheus

# Copy the Prometheus configuration file
COPY /conf/prometheus.yml /etc/prometheus/prometheus.yml

# Stage 3: Final image
FROM debian:buster

# Install necessary dependencies
RUN apt-get update && apt-get install -y curl

# Copy Grafana from Stage 1
COPY --from=grafana /usr/share/grafana /usr/share/grafana

# Copy the custom Grafana configuration file
COPY /conf/custom.ini /etc/grafana/custom.ini

# Copy Prometheus from Stage 2
COPY --from=prometheus /bin/prometheus /bin/prometheus
COPY --from=prometheus /etc/prometheus /etc/prometheus

# Copy the custom Grafana configuration file
COPY /conf/custom.ini /usr/share/grafana/conf/custom.ini

# Expose the ports for Grafana and Prometheus
EXPOSE 3000 9090

# Start both Grafana and Prometheus
CMD /usr/share/grafana/bin/grafana-server --config /usr/share/grafana/conf/custom.ini -homepath /usr/share/grafana & /bin/prometheus --config.file=/etc/prometheus/prometheus.yml