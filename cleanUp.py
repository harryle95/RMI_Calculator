import subprocess
from subprocess import check_output

CMD_TO_CLEAN = ["rmiregistry", "java CalculatorServer"]


def kill_process(command):
    """Kill background process performing input command

    Args:
        command (str): is an item in CMD_TO_CLEAN
    """
    print(f"Killing process running: {command}")
    processes = check_output("ps -ef| grep rmi", shell=True).decode("utf-8")
    processes = processes.split("\n")
    ids = []
    for proc in processes:
        if "grep" in proc:
            continue
        if proc == "":
            continue
        proc = proc.split(" ")
        proc = [item for item in proc if item != ""]
        pid = proc[1]
        ids.append(pid)

    if len(ids) == 0:
        return

    cmd = f"kill {' '.join(ids)}"
    print(cmd)
    subprocess.run(cmd, shell=True)


if __name__ == "__main__":
    for command in CMD_TO_CLEAN:
        kill_process(command)
