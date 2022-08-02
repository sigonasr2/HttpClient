export VARS=("")

function define() {
  VARS+=("$1")
  value="${*:2}"
  eval export "$1"='$value'
}

  echo "Dev build, no checks required."