variable "db_name" {
  default = "TainavDB"
}

variable "db_username" {
  default   = "Kilo"
  sensitive = true
}

variable "db_instance_class" {
  default = "db.t3.micro"
}

variable "allocated_storage" {
  default = 20
}

variable "aws_region" {
  default = "eu-central-1"
}
