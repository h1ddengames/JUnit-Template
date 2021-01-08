#!/usr/bin/env bash

chmod u+x ./.allure/allure-2.13.8/bin/allure
./.allure/allure-2.13.8/bin/allure generate docs/allure-results --clean -o docs/allure-reports && echo "[INFO] GENERATED ALLURE REPORT."