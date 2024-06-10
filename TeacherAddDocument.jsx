import React, { useEffect, useState } from 'react';

export default function TeacherAddDocument() {
    const [course, setCourse] = useState(null);
    const [subject, setSubject] = useState({});
    const [document, setDocument] = useState(null);
    const [errorMessage, setErrorMessage] = useState('');
    const [successMessage, setSuccessMessage] = useState('');
    const [courseSubjects , setCourseSubjects] = useState();

    const handleDocumentChange = (e) => {
        setDocument(e.target.files[0]);
    };
    console.log(course)
    console.log(subject.courseSubject.id)

    useEffect( ()=>{

        async function fetchData() {
            const res = await fetch("http://localhost:3001/teacher/get-course-subjects",{
                method:"POST",
                credentials:'include'
            })


            if(res.ok) {
                const courseSubjectsData = await res.json()
                setCourseSubjects(courseSubjectsData.coursesSubjects)
            }else {
                console.log(await res.json())
            }
        }
        fetchData()
    },[])

  const isValidName = (name) => /^[a-zA-Z0-9\s]+$/.test(name);

  const handleUpload = async () => {
    if (!course || !subject || !document) {
      setErrorMessage('Please fill all fields and select a document to upload.');
      setSuccessMessage('');
      return;
    }

    if (!isValidName(course) || !isValidName(subject)) {
      setErrorMessage('Course name and subject name should only contain letters, numbers, and spaces.');
      setSuccessMessage('');
      return;
    }

    const formData = new FormData();
    formData.append('courseSubjectId', subject.courseSubject.id);
    formData.append('document', document);

    try {
      const res = await fetch('http://localhost:3001/teacher/upload-notes', {
        method: 'POST',
        body: formData,
      });

      if (res.ok) {
        setSuccessMessage('Document uploaded successfully.');
        setErrorMessage('');
        setCourse({});
        setSubject({});
        setDocument(null);
      } else {
        setErrorMessage('Failed to upload document.');
        setSuccessMessage('');
      }
    } catch (error) {
      console.error('Error uploading document:', error);
      setErrorMessage('Failed to upload document.');
      setSuccessMessage('');
    }
  };


  return (
    <>
             <select onChange={(e) => setCourse(JSON.parse(e.target.value))}>
                <option value={null}> Select Course </option>
                { courseSubjects &&
                 courseSubjects.map(courseObj => (
                    <option value={JSON.stringify(courseObj)} >{courseObj.name}</option>

                ))}
            </select>
            <select onChange={(e) => setSubject(JSON.parse(e.target.value))} >
                <option> select subject </option>

                {course &&
                    course.subjects.map(subjectObj => (
                        <option value={JSON.stringify(subjectObj)} >{subjectObj.subject_name}</option>
                    ))
                }

            </select>

            <form>
                <input
                    type="file"
                    onChange={handleDocumentChange}
                    className="border border-gray-300 rounded p-2 w-full mb-4"
                />
                <div className='flex justify-center'>
                    <button
                        onClick={handleUpload}
                        className="bg-blue-500 text-white px-4 py-2 rounded"
                    >
                        Upload Document
                    </button>
                </div>

            </form>
    {/* <div className='flex justify-center min-h-screen pt-2'>
        <div className='w-full max-w-xl p-4'>
                <div className="card bg-white shadow-lg rounded-lg p-6">
                    <div className="text-center font-bold text-2xl mb-4">Upload Document</div>
                    {errorMessage && <div className="text-red-500 mb-4 text-center">{errorMessage}</div>}
                    {successMessage && <div className="text-green-500 mb-4 text-center">{successMessage}</div>}
                    <input
                        type="text"
                        value={courseName}
                        onChange={(e) => setCourseName(e.target.value)}
                        className="border border-gray-300 rounded p-2 w-full mb-4"
                        placeholder="Enter course name"
                    />
                    <input
                        type="text"
                        value={subjectName}
                        onChange={(e) => setSubjectName(e.target.value)}
                        className="border border-gray-300 rounded p-2 w-full mb-4"
                        placeholder="Enter subject name"
                    />
                    <input
                        type="file"
                        onChange={handleDocumentChange}
                        className="border border-gray-300 rounded p-2 w-full mb-4"
                    />
                    <div className='flex justify-center'>
                        <button
                            onClick={handleUpload}
                            className="bg-blue-500 text-white px-4 py-2 rounded"
                        >
                            Upload Document
                        </button>
                    </div>
                </div>
            </div>
        </div> */}
        </>
    );

}
