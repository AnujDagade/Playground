const fs = require('fs')
const { log } = require('console')
const { resolve } = require('path')

function mergeFile(file1, file2) {
    log('\x1b[33m',"File1", typeof(file1), file1)
    const mergedFile = fs.createWriteStream('mergedFile.txt')
    let data = ""
    mergedFile.on('error', (err) => {
        log('\x1b[31m',"Error while creating file",err)
    })


    const file1Data = fs.readFileSync(file1, 'utf-8')
    const file2Data =  fs.readFileSync(file2, 'utf-8')

    mergedFile.write(file1Data+file2Data)
    mergedFile.end()

    return new Promise((resolve, reject) => {
        mergedFile.on('finish', () => {
            log('\x1b[31m',"File merged successfully", process.cwd())
            data = fs.readFileSync('./mergedFile.txt', 'utf-8')
            log('\x1b[32m',"File data", data)
            resolve(data)
        })
    })

}

module.exports = mergeFile