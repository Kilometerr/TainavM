# Fetch PostgreSQL password from SSM Parameter Store
data "aws_ssm_parameter" "postgres_password" {
  name            = "SecretPostgreTainav"
  with_decryption = true
}

# Create PostgreSQL RDS instance
resource "aws_db_instance" "postgres" {
  identifier                      = "tainav-db"
  engine                          = "postgres"
  engine_version                  = "15"
  instance_class                  = var.db_instance_class
  allocated_storage               = var.allocated_storage
  db_name                         = var.db_name
  username                        = var.db_username
  password                        = data.aws_ssm_parameter.postgres_password.value
  publicly_accessible             = false
  skip_final_snapshot             = true
  storage_encrypted               = true
  vpc_security_group_ids = [aws_security_group.postgres_sg.id]
  backup_retention_period         = 7
  performance_insights_enabled    = true
  performance_insights_kms_key_id = aws_kms_key.rds_kms_key.arn
}

resource "aws_security_group" "postgres_sg" {
  name        = "postgres-sg"
  description = "Security group for PostgreSQL RDS"

  ingress {
    description = "Allow PostgreSQL access from application"
    from_port   = 5432
    to_port     = 5432
    protocol    = "tcp"
    cidr_blocks = ["10.0.0.0/16"] # Replace with your application CIDR
  }
}

resource "aws_kms_key" "rds_kms_key" {
  description             = "KMS key for RDS encryption"
  deletion_window_in_days = 10
  enable_key_rotation     = true
}