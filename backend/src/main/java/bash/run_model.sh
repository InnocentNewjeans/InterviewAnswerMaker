#!/bin/bash

# Log the start of the script
echo "Starting run_model.sh script" >&2

# Activate the conda environment
source ~/anaconda3/etc/profile.d/conda.sh
if [[ $? -ne 0 ]]; then
    echo "Failed to source conda.sh" >&2
    exit 1
fi

conda activate realfinal
if [[ $? -ne 0 ]]; then
    echo "Failed to activate conda environment" >&2
    exit 1
fi

# Log the activation of the environment
echo "Activated conda environment: realfinal" >&2

# Define the log file path
LOG_FILE="C:/Users/findu/Desktop/finalpjt/backend/src/bin/bash/script_output.log"

# Ensure log file directory exists
mkdir -p "$(dirname "$LOG_FILE")"

# Run the Python script with the provided arguments and log the output and errors to a log file
python C:/Users/findu/Desktop/finalpjt/ai_model/scripts/model.py "$1" "$2" > $LOG_FILE 2>&1
if [[ $? -ne 0 ]]; then
    echo "Python script execution failed" >&2
    cat $LOG_FILE >&2  # Output the log file to stderr
    exit 1
fi

# Log the end of the script
echo "Finished run_model.sh script" >&2
