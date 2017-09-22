#nux as the base image
FROM ubuntu:16.04
# Install the packages
RUN apt-get update && \
    apt-get -y install python && \
	apt-get -y install python-pip && \
	pip install flask && \
	pip install requests
# Open the following ports
EXPOSE 6000
# Add the files
ADD ./pyService.py /
# Define the command which runs when the container starts
CMD ["python pyService.py"]
# Use bash as the container's entry point
ENTRYPOINT ["/bin/bash", "-c"]
