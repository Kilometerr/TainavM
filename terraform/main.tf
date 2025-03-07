# Fetch PostgreSQL password from SSM Parameter Store
data "aws_ssm_parameter" "postgres_password" {
  name            = "SecretPostgreTainav"
  with_decryption = true
}

# Create PostgreSQL RDS instance
resource "aws_db_instance" "postgres" {
  identifier          = "tainav-db"
  engine              = "postgres"
  engine_version      = "15"
  instance_class      = var.db_instance_class
  allocated_storage   = var.allocated_storage
  db_name             = var.db_name
  username            = var.db_username
  password            = data.aws_ssm_parameter.postgres_password.value
  publicly_accessible = true
  skip_final_snapshot = true
}
