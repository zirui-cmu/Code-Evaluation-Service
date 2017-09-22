#nux as the base image
FROM ubuntu:16.04
# Install the packages
RUN apt-get update && \
    apt-get -y install python && \
	apt-get -y install python-pip && \
	pip install flask && \
	pip install requests
# Open the following ports
EXPOSE 5000
EXPOSE 80
# Add the files
ADD ./playground.py /
COPY site /site/
# Define the command which runs when the container starts
CMD ["python playground.py"]
# Use bash as the container's entry point
ENTRYPOINT ["/bin/bash", "-c"]
