# Document Versioning System

This project implements a simple **Document Versioning System** that allows tracking changes to a document over time. Each update creates a new version, and the system supports rollback, version retrieval, and listing available versions.  

---

## Difficulty Levels

The system can be extended to different difficulty levels, depending on the features you want to implement.

---

### **Basic**
- Single document versioning (in-memory storage).
- Supports:
  - `create(data)` – create the initial document.
  - `update(data)` – add a new version of the document.
  - `getVersion(v)` – retrieve content for a specific version.
  - `listVersions()` – list all available version numbers.
- Stores full content for each version.
- No rollback or diff optimization.

---

### **Easy**
- Adds rollback functionality to revert to a previous version. [x]
- Maintains version metadata:
  - Timestamps [x]
  - Operation type (`create`, `update`, etc.) [x]
- Handles invalid version accesses gracefully. [x]
- Optional: delete versions or rename the document. 

---

### **Moderate**
- Supports multiple documents within the system. [x]
- Persistent storage (file-based or database). [x]
- Differential storage: store only changes (deltas) instead of full copies. []
- Ability to compare versions (diff) and search document history. []
- Track changes per user if multi-user support is needed. []

---

### **Advanced**
- Handle concurrent editing with conflict detection.
- Branching and merging (similar to Git).
- Storage optimizations (compression, lazy loading of versions).
- REST API endpoints for external access to version data.
- Access control / authentication for multiple users.

---

### **Complex**
- Real-time collaborative editing using CRDTs or Operational Transform (OT).
- Fully distributed version control system.
- Complex conflict resolution, merge visualization, and revision graphs.
- Event sourcing / audit trail with rollback of arbitrary operations.
- Integration with external systems (notifications, audit logs, APIs).

---

## Summary

| Level      | Features                                                                 |
|------------|--------------------------------------------------------------------------|
| Basic      | Single doc, in-memory, create/update/get/list                             |
| Easy       | Rollback, version metadata, graceful error handling                      |
| Moderate   | Multi-doc, persistence, diff storage, search                             |
| Advanced   | Concurrency, branching/merging, REST APIs, access control                |
| Complex    | Real-time collaboration, distributed version control, CRDTs/OT, audit   |

---

This tiered structure allows the system to scale gradually from a simple in-memory solution to a full-fledged collaborative version control system.
