# hmpps-electronic-monitoring-data-platform-api
[![repo standards badge](https://img.shields.io/badge/dynamic/json?color=blue&style=flat&logo=github&label=MoJ%20Compliant&query=%24.result&url=https%3A%2F%2Foperations-engineering-reports.cloud-platform.service.justice.gov.uk%2Fapi%2Fv1%2Fcompliant_public_repositories%2Fhmpps-electronic-monitoring-data-platform-api)](https://operations-engineering-reports.cloud-platform.service.justice.gov.uk/public-github-repositories.html#hmpps-electronic-monitoring-data-platform-api "Link to report")
[![CircleCI](https://circleci.com/gh/ministryofjustice/hmpps-electronic-monitoring-data-platform-api/tree/main.svg?style=svg)](https://circleci.com/gh/ministryofjustice/hmpps-electronic-monitoring-data-platform-api)
[![Docker Repository on Quay](https://quay.io/repository/hmpps/hmpps-electronic-monitoring-data-platform-api/status "Docker Repository on Quay")](https://quay.io/repository/hmpps/hmpps-electronic-monitoring-data-platform-api)
[![API docs](https://img.shields.io/badge/API_docs_-view-85EA2D.svg?logo=swagger)](https://hmpps-electronic-monitoring-data-platform-api-dev.hmpps.service.justice.gov.uk/webjars/swagger-ui/index.html?configUrl=/v3/api-docs)

This is the API for the Electronic Monitoring project application.
- Currently in Discovery/Alpha phase
- Based off [hmpps-template-kotlin](https://github.com/ministryofjustice/hmpps-template-kotlin) - please see that project for original and setup instructions


## Before you commit
Our source control policy is to work via trunk-based development and pair programming, with frequent commits to main.

Aim to commit in small, regular chunks - a `red` --> `green` --> `refactor` cycle is a good increment.

If you are **not** pair programming, it's recommended to commit to a short-lived branch and merge in by PR.
- All linting must pass
- All code must have tests
- All tests must pass

## Resources
### Cloud resources
Resources for this app are defined in two other MoJ repositories:
- The HMPPS Cloud Platform namespace folders for this project([dev](https://github.com/ministryofjustice/cloud-platform-environments/tree/main/namespaces/live.cloud-platform.service.justice.gov.uk/hmpps-electronic-monitoring-dev), [preprod](https://github.com/ministryofjustice/cloud-platform-environments/tree/main/namespaces/live.cloud-platform.service.justice.gov.uk/hmpps-electronic-monitoring-preprod), & [prod](https://github.com/ministryofjustice/cloud-platform-environments/tree/main/namespaces/live.cloud-platform.service.justice.gov.uk/hmpps-electronic-monitoring-prod))
- [DPS Project Bootstrap /`projects.json`.](https://github.com/ministryofjustice/dps-project-bootstrap/blob/main/projects.json)
### Local resources
Locally this API runs using Docker containers. These resources are defined (for local deployment only) in [`Docker-compose.yml`](https://github.com/ministryofjustice/hmpps-electronic-monitoring-data-platform-api/blob/main/docker-compose.yml).
- A Kotlin API container (the main project)
- A Postgres DB container

### Environment variables and secrets
Cloud variables are defined in [`helm_deploy`](https://github.com/ministryofjustice/hmpps-electronic-monitoring-data-platform-api/tree/main/helm_deploy), as well as the above Cloud Platform and DPS Bootstrap repos


## Running & deploying the app
### How to run app locally
- Run docker compose up --build
- [TODO: add detail on how to run & troubleshoot gradle build]
- [TODO: add detail on running in debug mode]
### Deploying the app
The app is integrated with CircleCI for ci/cd build processes. [`.circleci/config.yml`](https://github.com/ministryofjustice/hmpps-electronic-monitoring-data-platform-api/blob/main/.circleci/config.yml) defines the build and deploy workflows in the project.


