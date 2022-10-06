const db = require("../models");
const Contact = db.contact;

exports.create = (req, res) => {
  if (!req.body.lastName) {
      res.status(400).send({ message: "Content must have field 'lastName'!" });
      return;
  }

  if (!req.body.firstName) {
    res.status(400).send({ message: "Content must have field 'firstName'!" });
    return;
  }

  if (!req.body.patronymic) {
    res.status(400).send({ message: "Content must have field 'patronymic'!" });
    return;
  }

  if (!req.body.phone) {
    res.status(400).send({ message: "Content must have field 'phone'!" });
    return;
  }

  const contact = new Contact({
    lastName: req.body.lastName,
    firstName: req.body.firstName,
    patronymic: req.body.patronymic,
    phone: req.body.phone
  });

  contact
      .save(contact)
      .then(data => {
        res.send(data);
      })
      .catch(err => {
        res.status(500).send({
          message:
              err.message || "Some error occurred while creating the Contact."
        });
      });
};

exports.findAll = (req, res) => {
  Contact.find({})
      .then(data => {
        res.send(data);
      })
      .catch(err => {
        res.status(500).send({
          message:
              err.message || "Some error occurred while retrieving contacts."
        });
      });
};

exports.findOne = (req, res) => {
  const id = req.params.id;

  Contact.findById(id)
      .then(data => {
        if (!data) {
          res.status(404).send({ message: `Not found Contact with id ${id}`});
        } else {
          res.send(data);
        }
      })
      .catch(err => {
        res.status(500).send({
          message:
            err.message || `Some error occurred while retrieving contact by id ${id}.`
        });
      });
};

exports.update = (req, res) => {
  if (!req.body) {
    return res.status(400).send({
      message: "Data to update can not be empty!"
    });
  }

  const id = req.params.id;

  Contact.findByIdAndUpdate(id, req.body, { useFindAndModify: false })
      .then(data => {
        if (!data) {
          res.status(404).send({
            message: `Cannot update Contact with id=${id}. Maybe Contact was not found!`
          });
        } else res.send({ message: "Contact was updated successfully." });
      })
      .catch(err => {
        res.status(500).send({
          message:
              err.message || "Error updating Contact with id=" + id
        });
      });
};

exports.delete = (req, res) => {
  const id = req.params.id;

  Contact.findByIdAndRemove(id)
      .then(data => {
        if (!data) {
          res.status(404).send({
            message: `Cannot delete Contact with id=${id}. Maybe Contact was not found!`
          });
        } else {
          res.send({
            message: "Contact was deleted successfully!"
          });
        }
      })
      .catch(err => {
        res.status(500).send({
          message:
              err.message || "Could not delete Contact with id=" + id
        });
      });
};

exports.deleteAll = (req, res) => {
  Contact.deleteMany({})
      .then(data => {
        res.send({
          message: `${data.deletedCount} Contacts were deleted successfully!`
        });
      })
      .catch(err => {
        res.status(500).send({
          message:
              err.message || "Some error occurred while removing all contacts."
        });
      });
};