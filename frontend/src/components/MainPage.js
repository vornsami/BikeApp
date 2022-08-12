import React, { useEffect, useState }  from 'react'

import Box from '@material-ui/core/Box'
import { useParams } from 'react-router-dom'

import getPaths from '../services/network'
import PathList from './PathList'

const MainPage = () => {
  let props = useParams();
  const pageLength = 24 

  const [paths, setPaths] = useState([])
  const [sort, setSort] = useState('Distance')
  const [page, setPage] = useState((props.page)? props.page : 1)

  useEffect(() => {
    async function fetchData() {
      const offset = page * pageLength - pageLength
      const initialPaths = await getPaths(sort, pageLength, offset)
      setPaths(initialPaths)
    }
    try {
      fetchData()
    } catch (e) {
      console.error(e)
    }
  }, [sort, page])
 
  return <Box>
      {paths !== undefined && <PathList paths={paths} setSort={setSort} sortBy={sort} page={page} setPage={setPage}/>}
    </Box>
}

export default MainPage